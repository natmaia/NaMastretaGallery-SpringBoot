package br.com.fiap.namastreta.models;

public record Token(
    String token,
    String type,
    String prefix
) {}