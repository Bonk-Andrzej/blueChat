package com.wildBirds.BlueChat.domain.model;


import com.wildBirds.BlueChat.api.rest.dto.PhotoDto;

class PhotoService {

    public PhotoDto toDto(Photo photo) {
        if (photo == null) {
            PhotoDto photoDto = new PhotoDto();
        return photoDto;
        }
        PhotoDto photoDto = new PhotoDto();
        if (photo.getIdPhoto() != null) {
            photoDto.setIdPhoto(photo.getIdPhoto());
        }
        if (photo.getPhoto() != null){
            photoDto.setPhoto(photo.getPhoto());
        }
        return photoDto;
    }

    public Photo toEntity(PhotoDto photoDto){
        Photo photo = new Photo();


        if (photoDto !=null && photoDto.getIdPhoto() != null){
            photo.setIdPhoto(photoDto.getIdPhoto());
        }
        if (photoDto !=null && photoDto.getPhoto() != null){
            photo.setPhoto(photoDto.getPhoto());
        }
        return photo;
    }
}
