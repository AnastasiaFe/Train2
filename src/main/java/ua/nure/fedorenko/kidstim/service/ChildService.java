package ua.nure.fedorenko.kidstim.service;

import ua.nure.fedorenko.kidstim.service.dto.ChildDTO;

public interface ChildService {

    void addChild(ChildDTO child);

    ChildDTO getChildById(String id);

    ChildDTO getChildByEmail(String email);

    ChildDTO updateChild(ChildDTO child);
}
