package controllers;

import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import models.Image;
import models.Sight;
import play.mvc.*;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;

/**
 *
 * @author nikola
 */
public class Images extends Controller {

    public static Result get(Long sightId) {
        Sight sight = Sight.find.byId(sightId);
        Image image = sight.getImage();
        response().setContentType("image/jpeg");
        response().setHeader("Content-Disposition", "filename=" + image.getName());
        return ok(image.getImageData());
    }

    public static Result save(Long sightId) {
        MultipartFormData body = request().body().asMultipartFormData();
        FilePart picture = body.getFile("file");
        if (picture != null) {
            String fileName = picture.getFilename();
            File file = picture.getFile();
            try {
                byte[] data = Files.toByteArray(file);
                Image image = new Image();
                image.setName(fileName);
                image.setImageData(data);
                image.save();

                Sight sight = Sight.find.byId(sightId);
                sight.setImage(image);
                sight.update();
            } catch (IOException ex) {
                return badRequest("Error");
            }
            return ok("Ok");
        } else {
            return badRequest("Error");
        }
    }
}
