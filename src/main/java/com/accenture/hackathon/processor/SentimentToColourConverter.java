package com.accenture.hackathon.processor;

public class SentimentToColourConverter {
    private static final int BASE_COLOUR = 120;

    public static String generateColor(double sentimentValue){
        int r;
        int g;
        int b;

        if (sentimentValue > 0) {
            r = (int) (BASE_COLOUR + -BASE_COLOUR * sentimentValue);
            g = (int) ((255 - BASE_COLOUR) * (sentimentValue*sentimentValue) + BASE_COLOUR);
            b = (int) (BASE_COLOUR + -BASE_COLOUR * sentimentValue);
        } else if (sentimentValue < 0) {
            r = (int) ((255 - BASE_COLOUR) * (sentimentValue*sentimentValue) + BASE_COLOUR);
            g = (int) (BASE_COLOUR + -BASE_COLOUR * -sentimentValue);
            b = (int) (BASE_COLOUR + -BASE_COLOUR * -sentimentValue);
        } else {
            r = BASE_COLOUR;
            g = BASE_COLOUR;
            b = BASE_COLOUR;
        }

        return String.format("#%02X%02X%02X", r, g, b);
    }
}
