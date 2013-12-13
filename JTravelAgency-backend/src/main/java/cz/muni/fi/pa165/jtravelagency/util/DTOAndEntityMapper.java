/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.util;

import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

/**
 *
 * @author mvaraga
 */
public class DTOAndEntityMapper {

    static {
        List myMappingFiles = new ArrayList();
        myMappingFiles.add("dozerBeanMapping.xml");
        mapper = new DozerBeanMapper(myMappingFiles);
    }
    static Mapper mapper;
    public static <T1, T2> T1 entityToDto(T2 srcObject, Class<T1> destClass) {
        if (srcObject == null) {
            return null;
        }      
        T1 destObject = mapper.map(srcObject, destClass);
        return destObject;
    }

    public static <T1, T2> T1 dtoToEntity(T2 dto, Class<T1> destClass) {
        return entityToDto(dto, destClass);
    }
    
}
