package auth.mappers;

import app.domain.model.ClinicalAnalysisLaboratory;
import auth.mappers.dto.ClinicalAnalysisLaboratoryDto;

import java.util.ArrayList;
import java.util.List;

/**
 * ClinicalAnalysisLaboratoryMapper class, which is responsible for converting a
 * ClinicalAnalysisLaboratoryList to ClinicalAnalysisLaboratoryDtoList.
 *
 * @author Nuno Pinho (1190918)
 */
public class ClinicalAnalysisLaboratoryMapper {

    /**
     * @param ClinicalAnalysisLaboratory a ClinicalAnalysisLaboratory List
     *
     * Responsible for converting a ClinicalAnalysisLaboratoryList containing
     * ClinicalAnalysisLaboratory Objects to ClinicalAnalysisLaboratoryDto
     * Objects
     *
     * @return a ClinicalAnalysisLaboratoryDto List which contains the converted
     * objects
     */
    public static List<ClinicalAnalysisLaboratoryDto> toDto(List<ClinicalAnalysisLaboratory> ClinicalAnalysisLaboratory) {
        List<ClinicalAnalysisLaboratoryDto> ClinicalAnalysisLaboratoryDto = new ArrayList<>();

        for (ClinicalAnalysisLaboratory ClinicalAnalysisLaboratoryEach : ClinicalAnalysisLaboratory) {
            ClinicalAnalysisLaboratoryDto.add(new ClinicalAnalysisLaboratoryDto(ClinicalAnalysisLaboratoryEach.getName(), ClinicalAnalysisLaboratoryEach.getAddress(), ClinicalAnalysisLaboratoryEach.getPhoneNumber(), ClinicalAnalysisLaboratoryEach.getTIN(), ClinicalAnalysisLaboratoryEach.getLabID()));
        }

        return ClinicalAnalysisLaboratoryDto;
    }
}
