package web.rest;

import model.Category;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/categories")
public class CategoryRestController {

    @GetMapping
    public List<Category> getCategories(){
        List<Category> categories = new ArrayList<>();

        categories.add(Category.FANTASY);
        categories.add(Category.NOVEL);
        categories.add(Category.THRILLER);
        categories.add(Category.BIOGRAPHY);
        categories.add(Category.CLASSICS);
        categories.add(Category.DRAMA);
        categories.add(Category.HISTORY);

        return categories;
    }
}
