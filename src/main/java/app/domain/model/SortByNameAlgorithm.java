package app.domain.model;

import auth.mappers.dto.ClientDto;

import java.util.List;

public class SortByNameAlgorithm {
    public SortByNameAlgorithm() { }
    public List<ClientDto> showList (List<ClientDto> clientListDto) {
        for (int a = 1; a < clientListDto.size(); a++) {
            for (int b = 0; b < clientListDto.size() - a; b++) {
                if (((clientListDto.get(b).getName()).compareTo((clientListDto.get(b+1).getName()))) > 0) {
                    // swap movies[b] with movies[b+1]
                    ClientDto temp = clientListDto.get(b);
                    clientListDto.set(b,clientListDto.get(b+1));
                    clientListDto.set(b+1, temp);
                }
            }
        }
        return clientListDto;
    }
}