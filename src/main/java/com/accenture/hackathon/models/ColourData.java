package com.accenture.hackathon.models;

import java.util.List;
import java.awt.*;
import com.accenture.hackathon.models.SentimentData;

public class ColourData {

    public static String generateColor(double sentimentValue){

        int R = 128;
        int G = 128;
        int B = 128;

        if (sentimentValue > 0) {

            R -= 127 * sentimentValue;
            G += 127 * sentimentValue;
            B -= 127 * sentimentValue;

        } else if (sentimentValue < 0) {

            R += 127 * sentimentValue;
            G -= 127 * sentimentValue;
            B -= 127 * sentimentValue;

        } else {
            R = 120;
            G = 120;
            B = 120;
        }

        return String.format("#%02X%02X%02X", R, G, B);
    }


}
