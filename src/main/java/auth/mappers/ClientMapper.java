package auth.mappers;


import app.domain.model.Client;
import auth.mappers.dto.ClientDto;

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

        Client c = new Client(citizenID, nhsID, birthDate, sex, TIN, phoneNumber, email, name);

        return c;
    }

    public static ClientDto toDto (Client client) {

        ClientDto c1 = new ClientDto(
                client.getCitizenID(),
                client.getNhsID(),
                client.getBirthDate(),
                client.getSex(),
                client.getTIN(),
                client.getPhoneNumber(),
                client.getEmail(),
                client.getName());

        return c1;

    }
}
