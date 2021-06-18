package auth.mappers;


import app.domain.model.Client;
import auth.mappers.dto.ClientDto;

import java.util.ArrayList;
import java.util.List;

public class ClientMapper {

    /**
     * Builds a client instance, receiving the clientDto.
     *
     * @param clientDto the clientDto
     * @return client instance
     */
    public static Client toModel (ClientDto clientDto ){

        String citizenID = clientDto.getCitizenID();
        String nhsID = clientDto.getNhsID();
        String birthDate = clientDto.getBirthDate();
        String sex = clientDto.getSex();
        String TIN = clientDto.getTIN();
        String phoneNumber = clientDto.getPhoneNumber();
        String email = clientDto.getEmail();
        String name = clientDto.getName();
        String address = clientDto.getAddress();

        Client c = new Client(citizenID, nhsID, birthDate, sex, TIN, phoneNumber, email, name, address);

        return c;
    }

    /**
     * Responsible for converting a client into a client Dto.
     *
     * @param client a client
     * @return a client Dto
     */
    public static ClientDto toDto (Client client) {

        ClientDto c1 = new ClientDto(
                client.getCitizenID(),
                client.getNhsID(),
                client.getBirthDate(),
                client.getSex(),
                client.getTIN(),
                client.getPhoneNumber(),
                client.getEmail(),
                client.getName(),
                client.getAddress());

        return c1;

    }
    public static List<ClientDto> toDTO (List<Client> clientList) {
        List<ClientDto> clientDtoList = new ArrayList<>();
        for (Client c : clientList) {
            ClientDto c1 = new ClientDto(c.getName(),c.getTIN());
            clientDtoList.add(c1);
        }
        return clientDtoList;

    }
}
