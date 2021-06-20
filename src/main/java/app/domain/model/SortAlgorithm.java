package app.domain.model;

import auth.mappers.dto.ClientDto;

import java.util.List;

public abstract class SortAlgorithm {
    public SortAlgorithm() { }

    public abstract List<ClientDto> showListByName(List<ClientDto> clientDtoList);

    public abstract List<ClientDto> showListByTIN(List<ClientDto> clientDtoList);
}
