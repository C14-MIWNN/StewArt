package nl.miwnn.se14.StewArt.stewart.service.mapper;

import nl.miwnn.se14.StewArt.stewart.dto.StewArtUserDTO;
import nl.miwnn.se14.StewArt.stewart.model.StewArtUser;

/**
 * @author Luc Weerts
 * Converts between Model classes and DTOs
 */
public class StewArtUserMapper {

    public static StewArtUser fromDTO(StewArtUserDTO dto) {
        StewArtUser user = new StewArtUser();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        return user;
    }
}
