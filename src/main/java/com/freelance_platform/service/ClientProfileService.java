package com.freelance_platform.service;

import com.freelance_platform.dto.ClientProfileResponse;
import com.freelance_platform.dto.ClientProfileUpdateRequest;
import com.freelance_platform.entity.Client;
import com.freelance_platform.entity.User;
import com.freelance_platform.repository.ClientRepository;
import com.freelance_platform.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class ClientProfileService {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;


    public ClientProfileService(
            ClientRepository clientRepository,
            UserRepository userRepository) {

        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
    }


    // ============================
    // GET CLIENT PROFILE
    // ============================

    public ClientProfileResponse getProfile(
            Integer userId) {

        Client client =
                clientRepository
                        .findByUserUserId(userId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Client profile not found."
                                )
                        );

        User user = client.getUser();

        ClientProfileResponse response =
                new ClientProfileResponse();


        response.setUserId(
                user.getUserId()
        );

        response.setClientId(
                client.getClientId()
        );

        response.setName(
                user.getName()
        );

        response.setEmail(
                user.getEmail()
        );

        response.setPhone(
                user.getPhone()
        );

        response.setProfilePicture(
                client.getProfilePicture()
        );

        response.setCompanyName(
                client.getCompanyName()
        );

        response.setCompanyDescription(
                client.getCompanyDescription()
        );

        response.setCompanyWebsite(
                client.getCompanyWebsite()
        );

        response.setLocation(
                client.getLocation()
        );

        response.setClientType(
                client.getClientType()
        );

        response.setVerificationStatus(
                client.getVerificationStatus()
        );


        return response;
    }


    // ============================
    // UPDATE CLIENT PROFILE
    // ============================

    public ClientProfileResponse updateProfile(
            Integer userId,
            ClientProfileUpdateRequest request) {

        Client client =
                clientRepository
                        .findByUserUserId(userId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Client profile not found."
                                )
                        );

        User user = client.getUser();


        // ----------------------------
        // USER INFORMATION
        // ----------------------------

        if (request.getName() != null) {

            user.setName(
                    request.getName()
            );
        }


        if (request.getPhone() != null) {

            user.setPhone(
                    request.getPhone()
            );
        }


        // ----------------------------
        // CLIENT INFORMATION
        // ----------------------------

        client.setProfilePicture(
                request.getProfilePicture()
        );

        client.setCompanyName(
                request.getCompanyName()
        );

        client.setCompanyDescription(
                request.getCompanyDescription()
        );

        client.setCompanyWebsite(
                request.getCompanyWebsite()
        );

        client.setLocation(
                request.getLocation()
        );

        client.setClientType(
                request.getClientType()
        );


        // ----------------------------
        // SAVE
        // ----------------------------

        userRepository.save(user);

        clientRepository.save(client);


        return getProfile(userId);
    }
}