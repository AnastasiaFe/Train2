package ua.nure.fedorenko.kidstim.service.impl;

import org.apache.log4j.Logger;
import ua.nure.fedorenko.kidstim.service.UserService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    @Override
    public BufferedImage getImage(String fileName) throws FileNotFoundException {
        File avatar = new File(fileName);
        BufferedImage image;
        try {
            image = ImageIO.read(avatar);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            throw new FileNotFoundException("Image with such name is not found!");
        }
        return image;
    }
}
