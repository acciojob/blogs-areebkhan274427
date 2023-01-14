package com.driver.services;

import com.driver.Dtos.ImageResponseDto;
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

    public ImageResponseDto createAndReturn(Blog blog, String description, String dimensions){
        //create an image based on given parameters and add it to the imageList of given blog
        Image image=new Image(description,dimensions);
        Blog blog1=blogRepository.findById(blog.getId()).get();
        image.setBlog(blog1);

        List<Image> imageList=blog1.getImageList();
        imageList.add(image);
        blog1.setImageList(imageList);

        blogRepository.save(blog1);


        ImageResponseDto imageResponseDto=new ImageResponseDto(image.getDescription(),
                image.getDimensions(),image.getBlog().getId());

        return imageResponseDto;
    }

    public void deleteImage(int id){
        if(imageRepository2.findById(id).isPresent())
        {
            imageRepository2.deleteById(id);
        }

    }

    public Image findById(int id) {
        return null;
    }

    public int countImagesInScreen(int id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image image=new Image();
        if(imageRepository2.findById(id).isPresent())
          image=imageRepository2.findById(id).get();
        else
            return 0;

        String imageSize=image.getDimensions();




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
