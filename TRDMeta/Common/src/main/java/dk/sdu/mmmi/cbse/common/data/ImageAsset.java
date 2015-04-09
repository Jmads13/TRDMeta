/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.sdu.mmmi.cbse.common.data;

/**
 *
 * @author SoA
 */
public final class ImageAsset {
    
    private String imageFilePath;

    public ImageAsset(String imageFilePath) {
            this.imageFilePath = imageFilePath;
    }

    public String getImageFilePath() {
            return imageFilePath;
    }
    
    public void setImageAsset(String s){
        imageFilePath = s;
    }
    
}
