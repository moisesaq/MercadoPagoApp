package com.example.moises.mercadopagoapp.model.cardIssuer;

import com.example.moises.mercadopagoapp.model.ParserContract;

/**
 * Later, remove all attributes unnecessary. But for now it is ok as example (Model backend)
 */
public class CardIssuerParser implements ParserContract<CardIssuer> {
    public String processing_mode;
    public String thumbnail;
    public String secure_thumbnail;
    public String name;
    public String id;

    @Override
    public CardIssuer getItem() {
        return new CardIssuer.Builder()
                .id(id)
                .name(name)
                .urlImage(thumbnail)
                .build();
    }
}
