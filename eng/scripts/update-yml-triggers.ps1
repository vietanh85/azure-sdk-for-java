[CmdletBinding()]
param (
    [string]$sdkRoot = "$PSScriptRoot\..\..\sdk"
)

function WriteYmlTriggerPaths($ymlFile, $includePaths, $excludePaths)
{
  $paths =  "`r`n`$1paths:"
  $paths += "`r`n`$1`$1include:"
  foreach ($path in $includePaths) {
    $paths += "`r`n`$1`$1`$1- $path"
  }
  $paths += "`r`n`$1`$1exclude:"
  foreach ($path in $excludePaths) {
    $paths += "`r`n`$1`$1`$1- $path"
  }
  $paths += "`r`n`r`n"

  $ymlContent = Get-Content $ymlFile -Raw
  $ymlContent = $ymlContent -replace "(?ms)\r\n(\s+)paths:.*?\r\n\r\n", $paths
  Set-Content -Path $ymlFile -Value $ymlContent -NoNewLine
}

function UpdateYmlTriggerPaths($serviceDirectory, $relativeRootIncludePath, $ymlFile, $directories, $rootServiceIncludeOnly = $false)
{
  $includePaths = @()
  $excludePaths = @()

  $includePaths += $relativeRootIncludePath + [System.IO.Path]::GetFileName($ymlFile)

  $pomFile = Join-Path $serviceDirectory "pom.xml"
  if (Test-Path $pomFile)
  {
    $excludePaths += $relativeRootIncludePath + "pom.xml"
  }

  foreach ($pkgDir in $directories)
  {
    $pkgDirIncludePath = $relativeRootIncludePath + $pkgDir.Name + "/"
    $includePaths += $pkgDirIncludePath

    $pomFile = Join-Path $pkgDir "pom.xml"
    if (Test-Path $pomFile)
    {
      $excludePaths += $pkgDirIncludePath + "pom.xml"
    }
  }

  if ($rootServiceIncludeOnly)
  {
    # If we only have a ci.yml make the include paths simpler
    $includePaths = @($relativeRootIncludePath)
  }

  WriteYmlTriggerPaths $ymlFile $includePaths $excludePaths
}

$serviceDirectories = Get-ChildItem $sdkRoot -Attributes Directory

foreach ($sd in $serviceDirectories)
{
  $serviceDirectory = $sd.Name
  $includePath = "sdk/${serviceDirectory}/"

  #if ($serviceDirectory -ne "keyvault") { continue }

  $ciYml = Join-Path $sd "ci.yml"
  $ciMgmtYml = Join-Path $sd "ci.mgmt.yml"
  $ciDataYml = Join-Path $sd "ci.data.yml"

  if (!(Test-Path $ciYml))
  {
    Write-Host "Skipping $($sd.Name) because there is no ci.yml"
    continue
  }

  $pkgDirectories = Get-ChildItem $sd -Attributes Directory

  $hasMgmt = $false
  $hasData = $false

  if (Test-Path $ciMgmtYml)
  {
    $hasMgmt = $true
    $mgmtPkgDirs = $pkgDirectories.Where({ $_.Name.StartsWith("mgmt-") })
    $pkgDirectories = $pkgDirectories.Where({ !$_.Name.StartsWith("mgmt-") })

    UpdateYmlTriggerPaths $sd $includePath $ciMgmtYml $mgmtPkgDirs
  }

  if (Test-Path $ciDataYml)
  {
    $hasData = $true
    $dataPkgDirs = $pkgDirectories.Where({ $_.Name.StartsWith("microsoft-") })
    $pkgDirectories = $pkgDirectories.Where({ !$_.Name.StartsWith("microsoft-") })

    UpdateYmlTriggerPaths $sd $includePath $ciDataYml $dataPkgDirs
  }

  $rootServiceIncludeOnly = $false
  if (!$hasMgmt -and !$hasData)
  {
    # If we only have a ci.yml make the include paths empty
    $rootServiceIncludeOnly = $true
  }

  UpdateYmlTriggerPaths $sd $includePath $ciYml $pkgDirectories -rootServiceIncludeOnly $rootServiceIncludeOnly
}

