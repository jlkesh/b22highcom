package dev.jlkeesh.dto.dto;

import dev.jlkeesh.dto.Dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleCreateDTO implements Dto {
    @NotBlank(message = "title.cannot.be.blank")
    @Size(message = "title.must.have.at.least.x.characters")
    private String title;
    @NotBlank(message = "overview.cannot.be.blank")
    @Size(message = "overview.must.have.at.least.x.characters")
    private String overview;

    @NotBlank(message = "content.cannot.be.blank")
    @Size(message = "content.must.have.at.least.x.characters")
    private String content;
    @NotNull(message = "picture.cannot.be.null")
    private MultipartFile pic;
}
