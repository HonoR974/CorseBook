package com.back.springboot.service;

import java.util.List;


import com.back.springboot.models.User;

public interface ContactService {
    

    //invitation 
    User setAskContact(long id_user);

    List<User> accepteDemande(long id);

    List<User> getInvitation();

    List<User> getInvitationById(long id);
   
    User refuseDemandeById(long id);

    //contact 
    List<User> getConctactsById(long id);

    List<User> getContactsByJwt();

    void deleteContactById(long idUser, long idToDelete);

    void deletedContactByJwt(long id);

    //suggest Contact 
    List<User> getSuggestContact();


    //get list user invité par l'user connecté 
    List<User> getListDemande();

    void cancelDemande(long id_toCancel);
    

}
