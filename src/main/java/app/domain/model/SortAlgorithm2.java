package app.domain.model;

import auth.mappers.dto.ClientDto;

import java.util.List;

public class SortAlgorithm2 extends SortAlgorithm{
    public SortAlgorithm2() { }

    List<ClientDto> list;
    int length;

    public List<ClientDto> showListByName(List<ClientDto> clientDtoList) {
        return sortByName(clientDtoList);
    }
    public List<ClientDto> showListByTIN(List<ClientDto> clientDtoList) {
        return sortByTIN(clientDtoList);
    }

    public List<ClientDto> sortByName(List<ClientDto> clientDtoList) {
        if (clientDtoList == null || clientDtoList.size() == 0) {
            return clientDtoList;
        }
        this.list =clientDtoList;
        this.length = clientDtoList.size();
        return quickSortName(clientDtoList, 0,  length - 1);
    }
    public List<ClientDto> sortByTIN(List<ClientDto> clientDtoList) {
        if (clientDtoList == null || clientDtoList.size() == 0) {
            return clientDtoList;
        }
        this.list =clientDtoList;
        this.length = clientDtoList.size();
        return quickSortTIN(clientDtoList, 0,  length - 1);
    }

    public List<ClientDto> quickSortName(List<ClientDto> list, int lowerIndex, int higherIndex) {
        int i = lowerIndex;
        int j = higherIndex;
        String pivot = this.list.get(lowerIndex + (higherIndex - lowerIndex) / 2).getName();

        while (i <= j) {
            while (this.list.get(i).getName().compareToIgnoreCase(pivot) < 0) {
                i++;
            }

            while (this.list.get(j).getName().compareToIgnoreCase(pivot) > 0) {
                j--;
            }

            if (i <= j) {
                exchange(list, i, j);
                i++;
                j--;
            }
        }

        if (lowerIndex < j) {
            quickSortName(list, lowerIndex, j);
        }
        if (i < higherIndex) {
            quickSortName(list, i, higherIndex);
        }
        return list;
    }
    public List<ClientDto> quickSortTIN(List<ClientDto> list, int lowerIndex, int higherIndex) {
        int i = lowerIndex;
        int j = higherIndex;
        String pivot = this.list.get(lowerIndex + (higherIndex - lowerIndex) / 2).getTIN();

        while (i <= j) {
            while (this.list.get(i).getTIN().compareToIgnoreCase(pivot) < 0) {
                i++;
            }

            while (this.list.get(j).getTIN().compareToIgnoreCase(pivot) > 0) {
                j--;
            }

            if (i <= j) {
                exchange(list, i, j);
                i++;
                j--;
            }
        }

        if (lowerIndex < j) {
            quickSortTIN(list, lowerIndex, j);
        }
        if (i < higherIndex) {
            quickSortTIN(list, i, higherIndex);
        }
        return list;
    }

    void exchange(List<ClientDto> list ,int i, int j) {
        ClientDto temp = this.list.get(i);
        this.list.set(i , list.get(j));
        this.list.set(j ,temp);
    }
}

