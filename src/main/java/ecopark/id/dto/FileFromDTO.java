package ecopark.id.dto;

import javax.ws.rs.FormParam;

public class FileFromDTO {
    @FormParam("file")
    public byte[] file;
}
