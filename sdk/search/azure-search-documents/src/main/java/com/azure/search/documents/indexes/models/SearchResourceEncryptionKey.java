// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.
// Changes may cause incorrect behavior and will be lost if the code is
// regenerated.

package com.azure.search.documents.indexes.models;

import com.azure.core.annotation.Fluent;
import com.azure.search.documents.indexes.implementation.models.AzureActiveDirectoryApplicationCredentials;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A customer-managed encryption key in Azure Key Vault. Keys that you create and manage can be used to encrypt or
 * decrypt data-at-rest in Azure Cognitive Search, such as indexes and synonym maps.
 */
@Fluent
public final class SearchResourceEncryptionKey {
    /*
     * The name of your Azure Key Vault key to be used to encrypt your data at
     * rest.
     */
    @JsonProperty(value = "keyVaultKeyName", required = true)
    private String keyName;

    /*
     * The version of your Azure Key Vault key to be used to encrypt your data
     * at rest.
     */
    @JsonProperty(value = "keyVaultKeyVersion", required = true)
    private String keyVersion;

    /*
     * The URI of your Azure Key Vault, also referred to as DNS name, that
     * contains the key to be used to encrypt your data at rest. An example URI
     * might be https://my-keyvault-name.vault.azure.net.
     */
    @JsonProperty(value = "keyVaultUri", required = true)
    private String vaultUrl;

    /*
     * Optional Azure Active Directory credentials used for accessing your
     * Azure Key Vault. Not required if using managed identity instead.
     */
    @JsonProperty(value = "accessCredentials")
    private AzureActiveDirectoryApplicationCredentials accessCredentials;

    /*
     * An explicit managed identity to use for this encryption key. If not
     * specified and the access credentials property is null, the
     * system-assigned managed identity is used. On update to the resource, if
     * the explicit identity is unspecified, it remains unchanged. If "none" is
     * specified, the value of this property is cleared.
     */
    @JsonProperty(value = "identity")
    private SearchIndexerDataIdentity identity;

    /**
     * Creates an instance of SearchResourceEncryptionKey class.
     *
     * @param keyName the keyName value to set.
     * @param keyVersion the keyVersion value to set.
     * @param vaultUrl the vaultUrl value to set.
     */
    @JsonCreator
    public SearchResourceEncryptionKey(
            @JsonProperty(value = "keyVaultKeyName", required = true) String keyName,
            @JsonProperty(value = "keyVaultKeyVersion", required = true) String keyVersion,
            @JsonProperty(value = "keyVaultUri", required = true) String vaultUrl) {
        this.keyName = keyName;
        this.keyVersion = keyVersion;
        this.vaultUrl = vaultUrl;
    }

    /**
     * Get the keyName property: The name of your Azure Key Vault key to be used to encrypt your data at rest.
     *
     * @return the keyName value.
     */
    public String getKeyName() {
        return this.keyName;
    }

    /**
     * Get the keyVersion property: The version of your Azure Key Vault key to be used to encrypt your data at rest.
     *
     * @return the keyVersion value.
     */
    public String getKeyVersion() {
        return this.keyVersion;
    }

    /**
     * Get the vaultUrl property: The URI of your Azure Key Vault, also referred to as DNS name, that contains the key
     * to be used to encrypt your data at rest. An example URI might be https://my-keyvault-name.vault.azure.net.
     *
     * @return the vaultUrl value.
     */
    public String getVaultUrl() {
        return this.vaultUrl;
    }

    /**
     * Get the identity property: An explicit managed identity to use for this encryption key. If not specified and the
     * access credentials property is null, the system-assigned managed identity is used. On update to the resource, if
     * the explicit identity is unspecified, it remains unchanged. If "none" is specified, the value of this property is
     * cleared.
     *
     * @return the identity value.
     */
    public SearchIndexerDataIdentity getIdentity() {
        return this.identity;
    }

    /**
     * Set the identity property: An explicit managed identity to use for this encryption key. If not specified and the
     * access credentials property is null, the system-assigned managed identity is used. On update to the resource, if
     * the explicit identity is unspecified, it remains unchanged. If "none" is specified, the value of this property is
     * cleared.
     *
     * @param identity the identity value to set.
     * @return the SearchResourceEncryptionKey object itself.
     */
    public SearchResourceEncryptionKey setIdentity(SearchIndexerDataIdentity identity) {
        this.identity = identity;
        return this;
    }

    /**
     * Get the applicationId property: An AAD Application ID that was granted the required access permissions to the
     * Azure Key Vault that is to be used when encrypting your data at rest. The Application ID should not be confused
     * with the Object ID for your AAD Application.
     *
     * @return the applicationId value.
     */
    public String getApplicationId() {
        return (this.accessCredentials == null) ? null : this.accessCredentials.getApplicationId();
    }

    /**
     * Set the applicationId property: An AAD Application ID that was granted the required access permissions to the
     * Azure Key Vault that is to be used when encrypting your data at rest. The Application ID should not be confused
     * with the Object ID for your AAD Application.
     *
     * @param applicationId the applicationId value to set.
     * @return the SearchResourceEncryptionKey object itself.
     */
    public SearchResourceEncryptionKey setApplicationId(String applicationId) {
        if (this.accessCredentials == null) {
            this.accessCredentials = new AzureActiveDirectoryApplicationCredentials();
        }

        this.accessCredentials.setApplicationId(applicationId);
        return this;
    }

    /**
     * Get the applicationSecret property: The authentication key of the specified AAD application.
     *
     * @return the applicationSecret value.
     */
    public String getApplicationSecret() {
        return (this.accessCredentials == null) ? null : this.accessCredentials.getApplicationSecret();
    }

    /**
     * Set the applicationSecret property: The authentication key of the specified AAD application.
     *
     * @param applicationSecret the applicationSecret value to set.
     * @return the SearchResourceEncryptionKey object itself.
     */
    public SearchResourceEncryptionKey setApplicationSecret(String applicationSecret) {
        if (this.accessCredentials == null) {
            this.accessCredentials = new AzureActiveDirectoryApplicationCredentials();
        }

        this.accessCredentials.setApplicationSecret(applicationSecret);
        return this;
    }
}
