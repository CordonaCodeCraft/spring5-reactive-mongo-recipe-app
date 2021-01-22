package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryTest {

    @Autowired
    CategoryReactiveRepository repository;

    @Before
    public void setUp() throws Exception {
        repository.deleteAll().block();
    }

    @Test
    public void testSaveCategory() {
        Category category = new Category();
        category.setDescription("Foo");
        repository.save(category).block();
        Long count = repository.count().block();
        assertEquals(Long.valueOf(1L), count);
    }

    @Test
    public void testFindCategoryByName() {
        //Given
        String description = "Foo";
        Category category = new Category();
        category.setDescription("Foo");
        repository.save(category).block();
        //When
        Category categoryFound = repository.findByDescription(description).block();
        //Then
        assertEquals(description, categoryFound.getDescription());
    }
}