package com.driver.services;


import com.driver.models.*;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository2;

    @Autowired
    BlogRepository blogRepository;

    public Image createAndReturn(Blog blog, String description, String dimensions){
        //create an image based on given parameters and add it to the imageList of given blog
        Image image=new Image(description,dimensions);
        Blog blog1;
        if(blogRepository.findById(blog.getId()).isPresent())
          blog1=blogRepository.findById(blog.getId()).get();
        else return null;

        image.setBlog(blog1);

        List<Image> imageList=blog1.getImageList();
        imageList.add(image);
        blog1.setImageList(imageList);

        blogRepository.save(blog1);

        return image;
    }

    public void deleteImage(Image image){
        if(imageRepository2.findById(image.getId()).isPresent())
        {
            imageRepository2.deleteById(image.getId());
        }

    }

    public Image findById(int id) {
        if(imageRepository2.findById(id).isPresent())
         return imageRepository2.findById(id).get();

        return null;

    }

    public int countImagesInScreen(Image image, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image image1;

            image1=imageRepository2.findById(image.getId()).get();


        String imageSize=image1.getDimensions();


        int x=0;
        for(int i=0;i<imageSize.length();i++)
        {
            char c=imageSize.charAt(i);
            if(c=='x'|| c=='X')
            {
                x=i;
                break;
            }
        }
        int first=Integer.valueOf(imageSize.substring(0,x));
        int second=Integer.valueOf(imageSize.substring(x+1));

        int finalImageDimension=first*second;

        x=0;

        for(int i=0;i<screenDimensions.length();i++)
        {
            char c=screenDimensions.charAt(i);
            if(c=='x'|| c=='X')
            {
                x=i;
                break;
            }
        }
        first=Integer.valueOf(screenDimensions.substring(0,x));
        second=Integer.valueOf(screenDimensions.substring(x+1));

        int finalScreenDimension=first*second;

        int count=finalScreenDimension/finalImageDimension;

        return count;

        //In case the image is null, return 0


    }
}
