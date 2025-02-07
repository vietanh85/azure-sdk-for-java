// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.messaging.webpubsub;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.azure.messaging.webpubsub.implementation.WebPubSubsImpl;
import com.azure.messaging.webpubsub.models.GetAuthenticationTokenOptions;
import com.azure.messaging.webpubsub.models.WebPubSubAuthenticationToken;

/** Initializes a new instance of the synchronous AzureWebPubSubServiceRestAPI type. */
@ServiceClient(builder = WebPubSubServiceClientBuilder.class)
public final class WebPubSubServiceClient {
    private final WebPubSubsImpl serviceClient;
    private final String endpoint;
    private final WebPubSubAuthenticationPolicy webPubSubAuthPolicy;
    private final String hub;

    /**
     * Initializes an instance of WebPubSubs client.
     * @param serviceClient the service client implementation.
     */
    WebPubSubServiceClient(WebPubSubsImpl serviceClient, String hub, String endpoint,
                           WebPubSubAuthenticationPolicy webPubSubAuthPolicy) {
        this.serviceClient = serviceClient;
        this.endpoint = endpoint;
        this.webPubSubAuthPolicy = webPubSubAuthPolicy;
        this.hub = hub;
    }

    /**
     * Creates an authentication token.
     * @param options Options to apply when creating the authentication token.
     * @return A new authentication token instance.
     */
    public WebPubSubAuthenticationToken getAuthenticationToken(GetAuthenticationTokenOptions options) {
        final String endpoint = this.endpoint.endsWith("/") ? this.endpoint : this.endpoint + "/";
        final String audience = endpoint + "client/hubs/" + hub;

        final String authToken = WebPubSubAuthenticationPolicy.getAuthenticationToken(
                audience, options, webPubSubAuthPolicy.getCredential());

        // The endpoint should always be http or https and client endpoint should be ws or wss respectively.
        final String clientEndpoint = endpoint.replaceFirst("http", "ws");
        final String clientUrl = clientEndpoint + "client/hubs/" + hub;

        final String url = clientUrl + "?access_token=" + authToken;

        return new WebPubSubAuthenticationToken(authToken, url);
    }

    /**
     * Broadcast content inside request body to all the connected client connections.
     * @param message The payload body.
     * @param contentType Upload file type.
     * @param contentLength The contentLength parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> sendToAllWithResponse(
            BinaryData message,
            String contentType,
            long contentLength,
            RequestOptions requestOptions,
            Context context) {
        return this.serviceClient.sendToAllWithResponse(
                hub, contentType, message, contentLength, requestOptions, context);
    }

    /**
     * Broadcast content inside request body to all the connected client connections.
     * @param message The payload body.
     * @param contentType Upload file type.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void sendToAll(String message, String contentType) {
        sendToAllWithResponse(BinaryData.fromString(message),
                new RequestOptions().addRequestCallback(request -> request.getHeaders().set("Content-Type",
                        contentType)), Context.NONE);
    }

    /**
     * Broadcast content inside request body to all the connected client connections.
     * @param message The payload body.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> sendToAllWithResponse(
            BinaryData message, RequestOptions requestOptions, Context context) {
        return this.serviceClient.sendToAllWithResponse(hub, message, requestOptions, context);
    }

    /**
     * Check if the connection with the given connectionId exists.
     * @param connectionId The connection Id.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Boolean> connectionExistsWithResponse(
            String connectionId, RequestOptions requestOptions, Context context) {
        return this.serviceClient.connectionExistsWithResponse(hub, connectionId, requestOptions, context);
    }

    /**
     * Close the client connection.
     * @param connectionId Target connection Id.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> closeConnectionWithResponse(
            String connectionId, RequestOptions requestOptions, Context context) {
        return this.serviceClient.closeClientConnectionWithResponse(hub, connectionId, requestOptions, context);
    }

    /**
     * Send content inside request body to the specific connection.
     * @param connectionId The connection Id.
     * @param message The payload body.
     * @param contentType Upload file type.
     * @param contentLength The contentLength parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> sendToConnectionWithResponse(
            String connectionId,
            BinaryData message,
            String contentType,
            long contentLength,
            RequestOptions requestOptions,
            Context context) {
        return this.serviceClient.sendToConnectionWithResponse(
                hub, connectionId, contentType, message, contentLength, requestOptions, context);
    }

    /**
     * Send content inside request body to the specific connection.
     * @param connectionId The connection Id.
     * @param message The payload body.
     * @param contentType Upload file type.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void sendToConnection(
            String connectionId, String message, String contentType) {
        this.sendToConnectionWithResponse(connectionId, BinaryData.fromString(message),
                new RequestOptions().addRequestCallback(request -> request.getHeaders()
                        .set("Content-Type", contentType)), Context.NONE);
    }

    /**
     * Send content inside request body to the specific connection.
     * @param connectionId The connection Id.
     * @param message The payload body.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> sendToConnectionWithResponse(
            String connectionId, BinaryData message, RequestOptions requestOptions, Context context) {
        return this.serviceClient.sendToConnectionWithResponse(hub, connectionId, message, requestOptions, context);
    }

    /**
     * Check if there are any client connections inside the given group.
     * @param group Target group name, which length should be greater than 0 and less than 1025.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Boolean> groupExistsWithResponse(
            String group, RequestOptions requestOptions, Context context) {
        return this.serviceClient.groupExistsWithResponse(hub, group, requestOptions, context);
    }

    /**
     * Send content inside request body to a group of connections.
     * @param group Target group name, which length should be greater than 0 and less than 1025.
     * @param message The payload body.
     * @param contentType Upload file type.
     * @param contentLength The contentLength parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> sendToGroupWithResponse(
            String group,
            BinaryData message,
            String contentType,
            long contentLength,
            RequestOptions requestOptions,
            Context context) {
        return this.serviceClient.sendToGroupWithResponse(
                hub, group, contentType, message, contentLength, requestOptions, context);
    }

    /**
     * Send content inside request body to a group of connections.
     * @param group Target group name, which length should be greater than 0 and less than 1025.
     * @param message The payload body.
     * @param contentType Upload file type.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void sendToGroup(String group, String message, String contentType) {
        sendToGroupWithResponse(group, BinaryData.fromString(message), new RequestOptions()
                .addRequestCallback(request -> request.getHeaders().set("Content-Type", contentType)), Context.NONE);
    }

    /**
     * Send content inside request body to a group of connections.
     * @param group Target group name, which length should be greater than 0 and less than 1025.
     * @param message The payload body.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> sendToGroupWithResponse(
            String group, BinaryData message, RequestOptions requestOptions, Context context) {
        return this.serviceClient.sendToGroupWithResponse(hub, group, message, requestOptions, context);
    }

    /**
     * Add a connection to the target group.
     * @param group Target group name, which length should be greater than 0 and less than 1025.
     * @param connectionId Target connection Id.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> addConnectionToGroupWithResponse(
            String group, String connectionId, RequestOptions requestOptions, Context context) {
        return this.serviceClient.addConnectionToGroupWithResponse(hub, group, connectionId, requestOptions, context);
    }

    /**
     * Remove a connection from the target group.
     * @param group Target group name, which length should be greater than 0 and less than 1025.
     * @param connectionId Target connection Id.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> removeConnectionFromGroupWithResponse(
            String group, String connectionId, RequestOptions requestOptions, Context context) {
        return this.serviceClient.removeConnectionFromGroupWithResponse(
                hub, group, connectionId, requestOptions, context);
    }

    /**
     * Check if there are any client connections connected for the given user.
     * @param userId Target user Id.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Boolean> userExistsWithResponse(
            String userId, RequestOptions requestOptions, Context context) {
        return this.serviceClient.userExistsWithResponse(hub, userId, requestOptions, context);
    }

    /**
     * Send content inside request body to the specific user.
     * @param userId The user Id.
     * @param message The payload body.
     * @param contentType Upload file type.
     * @param contentLength The contentLength parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> sendToUserWithResponse(
            String userId,
            BinaryData message,
            String contentType,
            long contentLength,
            RequestOptions requestOptions,
            Context context) {
        return this.serviceClient.sendToUserWithResponse(
                hub, userId, contentType, message, contentLength, requestOptions, context);
    }

    /**
     * Send content inside request body to the specific user.
     * @param userId The user Id.
     * @param message The payload body.
     * @param contentType Upload file type.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void sendToUser(String userId, String message, String contentType) {
        sendToUserWithResponse(userId, BinaryData.fromString(message), new RequestOptions()
                .addRequestCallback(request -> request.getHeaders().set("Content-Type", contentType)), Context.NONE);
    }

    /**
     * Send content inside request body to the specific user.
     * @param userId The user Id.
     * @param message The payload body.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> sendToUserWithResponse(
            String userId, BinaryData message, RequestOptions requestOptions, Context context) {
        return this.serviceClient.sendToUserWithResponse(hub, userId, message, requestOptions, context);
    }

    /**
     * Add a user to the target group.
     * @param group Target group name, which length should be greater than 0 and less than 1025.
     * @param userId Target user Id.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> addUserToGroupWithResponse(
            String group, String userId, RequestOptions requestOptions, Context context) {
        return this.serviceClient.addUserToGroupWithResponse(hub, group, userId, requestOptions, context);
    }

    /**
     * Remove a user from the target group.
     * @param group Target group name, which length should be greater than 0 and less than 1025.
     * @param userId Target user Id.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> removeUserFromGroupWithResponse(
            String group, String userId, RequestOptions requestOptions, Context context) {
        return this.serviceClient.removeUserFromGroupWithResponse(hub, group, userId, requestOptions, context);
    }

    /**
     * Remove a user from all groups.
     * @param userId Target user Id.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> removeUserFromAllGroupsWithResponse(
            String userId, RequestOptions requestOptions, Context context) {
        return this.serviceClient.removeUserFromAllGroupsWithResponse(hub, userId, requestOptions, context);
    }

    /**
     * Grant permission to the connection.
     * @param permission The permission: current supported actions are joinLeaveGroup and sendToGroup.
     * @param connectionId Target connection Id.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> grantPermissionWithResponse(
            String permission, String connectionId, RequestOptions requestOptions, Context context) {
        return this.serviceClient.grantPermissionWithResponse(hub, permission, connectionId, requestOptions, context);
    }

    /**
     * Revoke permission for the connection.
     * @param permission The permission: current supported actions are joinLeaveGroup and sendToGroup.
     * @param connectionId Target connection Id.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> revokePermissionWithResponse(
            String permission, String connectionId, RequestOptions requestOptions, Context context) {
        return this.serviceClient.revokePermissionWithResponse(hub, permission, connectionId, requestOptions, context);
    }

    /**
     * Check if a connection has permission to the specified action.
     * @param permission The permission: current supported actions are joinLeaveGroup and sendToGroup.
     * @param connectionId Target connection Id.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Boolean> checkPermissionWithResponse(
            String permission, String connectionId, RequestOptions requestOptions, Context context) {
        return this.serviceClient.checkPermissionWithResponse(hub, permission, connectionId, requestOptions, context);
    }
}
