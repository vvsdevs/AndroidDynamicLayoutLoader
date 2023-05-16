package com.dynamiclayout.loader.network;

public abstract class ApiResponse {

    public abstract void onSuccess(String response);

    public abstract void onError(String message);
}