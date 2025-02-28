package ro.ps.lab3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
/**
 * Data transfer object (DTO) representing a collection response.
 * @param <T> The type of items in the collection.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CollectionResponseDTO<T> {

    private List<T> items;
    private long total;
}
