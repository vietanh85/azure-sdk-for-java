// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.security.attestation.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.Base64Url;
import com.azure.core.util.CoreUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Defines the "run time data" provided by the attestation target for use by the MAA. */
@Fluent
public final class RuntimeData {
    /*
     * UTF-8 encoded Runtime Data generated by the trusted environment
     */
    @JsonProperty(value = "data")
    private Base64Url data;

    /*
     * The type of data contained within the "data" field
     */
    @JsonProperty(value = "dataType")
    private DataType dataType;

    /**
     * Get the data property: UTF-8 encoded Runtime Data generated by the trusted environment.
     *
     * @return the data value.
     */
    public byte[] getData() {
        if (this.data == null) {
            return null;
        }
        return this.data.decodedBytes();
    }

    /**
     * Set the data property: UTF-8 encoded Runtime Data generated by the trusted environment.
     *
     * @param data the data value to set.
     * @return the RuntimeData object itself.
     */
    public RuntimeData setData(byte[] data) {
        if (data == null) {
            this.data = null;
        } else {
            this.data = Base64Url.encode(CoreUtils.clone(data));
        }
        return this;
    }

    /**
     * Get the dataType property: The type of data contained within the "data" field.
     *
     * @return the dataType value.
     */
    public DataType getDataType() {
        return this.dataType;
    }

    /**
     * Set the dataType property: The type of data contained within the "data" field.
     *
     * @param dataType the dataType value to set.
     * @return the RuntimeData object itself.
     */
    public RuntimeData setDataType(DataType dataType) {
        this.dataType = dataType;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() { }

    /**
     * Create a implementation type from a public type.
     * @return implementation type.
     */
    public com.azure.security.attestation.implementation.models.RuntimeData toGenerated() {
        return new com.azure.security.attestation.implementation.models.RuntimeData()
            .setData(data.decodedBytes())
            .setDataType(com.azure.security.attestation.implementation.models.DataType.fromString(dataType.toString()));
    }
}
