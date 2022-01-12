package org.internship.library.app;

import java.sql.Date;

import org.internship.library.api.dto.BookDTO;
import org.internship.library.app.adapter.AuthorMapper;
import org.internship.library.app.persistence.entity.AuthorEntity;
import org.internship.library.app.persistence.entity.BorrowEntity;
import org.internship.library.app.persistence.entity.LinkBorrowEntity;
import org.internship.library.app.persistence.entity.StockEntity;
import org.internship.library.app.persistence.entity.UserEntity;
import org.internship.library.app.persistence.repository.AuthorRepository;
import org.internship.library.app.persistence.repository.BookRepositoryImpl;
import org.internship.library.app.persistence.repository.BorrowRepository;
import org.internship.library.app.persistence.repository.LinkBorrowRepository;
import org.internship.library.app.persistence.repository.StockRepository;
import org.internship.library.app.persistence.repository.UserRepository;
import org.internship.library.app.security.ApplicationPasswordEncoder;
import org.internship.library.app.security.UserRole;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@SpringBootTest(classes = {LibraryAppConfig.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LibraryAppConfigTest
{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ApplicationPasswordEncoder applicationPasswordEncoder;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepositoryImpl bookRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private BorrowRepository borrowRepository;
    @Autowired
    private LinkBorrowRepository linkBorrowRepository;

    public static final String testUserName = "Test123!";
    public static final String testUserPassword = "Test123!";
    public static final String testUserEmail = "userTest@gmail.com";
    public static final String testUserRole = "USER";

    public static final String authorFirstNameTest = "authorFirstNameTest";
    public static final String authorLastNameTest = "authorLastNameTest";

    public static final String bookIdTest = "Integration Testing!";
    public static final Integer bookNumberOfPagesTest = 250;
    public static final String bookTitleTest = "bookTitleTest";

    public static final Integer stockNumberOfBooksTest = 350;

    public static final Long milis = System.currentTimeMillis();
    public static final Date borrowDateBorrowedTest = new Date(milis);
    public static final Date borrowToBeReturnedTest = new Date(milis);
    public static final Boolean borrowReturnedTest = true;
    public static final Boolean borrowReturnedOnTime = true;

    protected UserEntity userEntity;
    protected AuthorEntity authorEntity;
    protected BookDTO bookDTO;
    protected StockEntity stockEntity;
    protected BorrowEntity borrowEntity;
    protected LinkBorrowEntity linkBorrowEntity;

    @BeforeAll
    void beforeAll()
    {
        createUser();
        createAuthor();
        createBook();
        createStock();
        createBorrow();
        createLinkBorrow();
    }

    @AfterAll
    public void afterAll()
    {
        stockRepository.delete(stockEntity);
        authorRepository.delete(authorEntity);
        userRepository.delete(userEntity);

        //        bookRepository.deleteBook(bookDTO.getId());
        //        borrowRepository.delete(borrowEntity);
        //        linkBorrowRepository.delete(linkBorrowEntity);
    }

    public void createUser()
    {
        this.userEntity = new UserEntity();
        userEntity.setUserName(testUserName);
        userEntity.setPassword(applicationPasswordEncoder.encode(testUserPassword));
        userEntity.setEmail(testUserEmail);
        userEntity.setUserRole(UserRole.valueOf(testUserRole));
        userEntity = userRepository.save(userEntity);

    }

    public void createAuthor()
    {
        this.authorEntity = new AuthorEntity();
        authorEntity.setFirst_name(authorFirstNameTest);
        authorEntity.setLast_name(authorLastNameTest);
        authorEntity = authorRepository.save(authorEntity);

    }

    public void createBook()
    {
        this.bookDTO = new BookDTO();
        bookDTO.setTitle(bookTitleTest);
        bookDTO.setNumberOfPages(bookNumberOfPagesTest);
        bookDTO.setAuthor(AuthorMapper.authorEntityToAuthorDTO(authorEntity));
        bookDTO = bookRepository.createBook(bookDTO);
    }

    public void createStock()
    {
        this.stockEntity = new StockEntity();
        stockEntity.setNumberOfBooks(stockNumberOfBooksTest);
        stockEntity.setBookID(bookDTO.getId());
        stockEntity = stockRepository.save(stockEntity);

    }

    public void createBorrow()
    {
        this.borrowEntity = new BorrowEntity();
        borrowEntity.setDate_borrowed(borrowDateBorrowedTest);
        borrowEntity.setTo_be_returned(borrowToBeReturnedTest);
        borrowEntity.setReturned(borrowReturnedTest);
        borrowEntity.setReturned_on_time(borrowReturnedOnTime);
        borrowEntity = borrowRepository.save(borrowEntity);

    }

    public void createLinkBorrow()
    {
        this.linkBorrowEntity = new LinkBorrowEntity();
        linkBorrowEntity.setBook(bookDTO.getId());
        linkBorrowEntity.setBorrow(borrowEntity.getId());
        linkBorrowEntity.setUser(userEntity.getId());
        linkBorrowEntity = linkBorrowRepository.save(linkBorrowEntity);
    }
}
