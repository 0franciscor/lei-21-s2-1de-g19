package app.domain.model;

import auth.mappers.dto.ClientDto;

import java.util.List;

public class SortAlgorithm1 extends SortAlgorithm {
    public SortAlgorithm1()  { }
    public List<ClientDto> showListByName  (List<ClientDto> clientListDto) {
        for (int a = 1; a < clientListDto.size(); a++) {
            for (int b = 0; b < clientListDto.size() - a; b++) {
                if (((clientListDto.get(b).getName()).compareTo((clientListDto.get(b+1).getName()))) > 0) {
                    ClientDto temp = clientListDto.get(b);
                    clientListDto.set(b,clientListDto.get(b+1));
                    clientListDto.set(b+1, temp);
                }
            }
        }
        return clientListDto;
    }
    public List<ClientDto> showListByTIN (List<ClientDto> clientListDto) {
        for (int a = 1; a < clientListDto.size(); a++) {
            for (int b = 0; b < clientListDto.size() - a; b++) {
                if (((clientListDto.get(b).getTIN()).compareTo((clientListDto.get(b+1).getTIN()))) > 0) {
                    ClientDto temp = clientListDto.get(b);
                    clientListDto.set(b,clientListDto.get(b+1));
                    clientListDto.set(b+1, temp);
                }
            }
        }
        return clientListDto;
    }
}