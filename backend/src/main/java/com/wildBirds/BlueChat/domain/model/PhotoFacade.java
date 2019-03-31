package com.wildBirds.BlueChat.domain.model;


public class PhotoFacade {

    private PhotoRepository photoRepository;
    private PhotoService photoService;

    public PhotoFacade(PhotoRepository photoRepository, PhotoService photoService) {
        this.photoRepository = photoRepository;
        this.photoService = photoService;
    }

    protected Photo generatePhoto() {
        Photo photo = new Photo();
        photo.setPhoto(generateColor());
        Photo save = photoRepository.save(photo);
        return save;
    }
    protected Photo checkPhoto(String color) {
        Photo photo = new Photo();
        photo.setPhoto(color);
        Photo result = photoRepository.findByPhoto(color);
        System.out.println("PRZED >>>>>" + result);
        if (result == null) {
            result = photoRepository.save(photo);
            System.out.println("PO >>>>>>" + result);
        }
        return result;
    }
    private String generateColor() {
        return "rgb("+part()+(",")+part()+(",") +part() +")";
    }

    private static int part() {
        int part = (int) (Math.random() * 256);
        return part;
    }
}
