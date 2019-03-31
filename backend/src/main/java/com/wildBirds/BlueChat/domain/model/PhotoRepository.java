package com.wildBirds.BlueChat.domain.model;

import org.springframework.data.jpa.repository.JpaRepository;

interface PhotoRepository extends JpaRepository<Photo, Long> {

    public Photo findByPhoto(String photo);
}
