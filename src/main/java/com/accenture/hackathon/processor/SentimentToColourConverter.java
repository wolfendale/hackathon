package com.accenture.hackathon.processor;

import java.util.List;
import java.awt.*;
import com.accenture.hackathon.models.SentimentData;

public class SentimentToColourConverter {
    public static String generateColor(double sentimentValue){

        int r;
        int g;
        int b;

        if (sentimentValue > 0) {
            r = (int) (120 + -120 * sentimentValue);
            g = (int) ((255 - 120) * sentimentValue + 120);
            b = (int) (120 + -120 * sentimentValue);
        } else if (sentimentValue < 0) {
            r = (int) ((255 - 120) * -sentimentValue + 120);
            g = (int) (120 + -120 * -sentimentValue);
            b = (int) (120 + -120 * -sentimentValue);
        } else {
            r = 120;
            g = 120;
            b = 120;
        }

        return String.format("#%02X%02X%02X", r, g, b);
    }


}
