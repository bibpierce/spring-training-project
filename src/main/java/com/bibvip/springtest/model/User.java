package com.bibvip.springtest.model;


import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String name;
    private byte[] picture;
    private byte[] vacCard;
    private byte[] primaryId;
    private byte[]secondaryId;
    private String picFilename;
    private String vacFilename;
    private String prmFilename;
    private String secFilename;



}
