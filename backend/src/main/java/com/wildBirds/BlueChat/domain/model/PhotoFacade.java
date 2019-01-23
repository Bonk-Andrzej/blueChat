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

//    public PhotoDto addRandomPhotoToUser(User user){
//        Photo photo = new Photo();
//        photo.setPhoto(generateColor());
//        Photo photoProfile = photoRepository.save(photo);
//
//        user.setProfilePhoto(photoProfile);

//    }







    private String generateColor() {
        return "rgb("+part()+(",")+part()+(",") +part() +")";
    }

    private static int part() {
        int part = (int) (Math.random() * 256);
        return part;
    }
}
