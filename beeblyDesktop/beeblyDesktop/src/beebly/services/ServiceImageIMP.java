/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beebly.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author ahmed
 */
public class ServiceImageIMP {
    
    public boolean isImage(byte[] bytes) {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes)) {
            return ImageIO.read(bis) != null;
        } catch (IOException e) {
            // handle the exception appropriately
            return false;
        }
    }
    
}
