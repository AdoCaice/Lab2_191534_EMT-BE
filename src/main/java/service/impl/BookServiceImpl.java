package service.impl;


import model.Author;
import model.Book;
import model.Category;
import model.dto.BookDto;
import model.exceptions.AuthorNotFoundException;
import model.exceptions.BookNotFoundException;
import org.springframework.stereotype.Service;
import repository.AuthorRepository;
import repository.BookRepository;
import service.BookService;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> create(BookDto bookDto) {
        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));

        this.bookRepository.deleteBookByName(bookDto.getName());
        Book book = new Book(bookDto.getName(), bookDto.getCategory(), author, bookDto.getCopiesAvailable());
        this.bookRepository.save(book);
        return Optional.of(book);

    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));

        book.setName(bookDto.getName());
        book.setAuthor(author);
        book.setCopiesAvailable(bookDto.getCopiesAvailable());
        book.setCategory(bookDto.getCategory());

        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> create(String name, Category category, Long author, Integer avCopies) {
        Author author1 = this.authorRepository.findById(author)
                .orElseThrow(() -> new AuthorNotFoundException(author));
        Book book = new Book(name,category,author1,avCopies);
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, String name, Category category, Long author, Integer avCopies) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        Author author1 = this.authorRepository.findById(author)
                .orElseThrow(() -> new AuthorNotFoundException(author));

        book.setName(name);
        book.setAuthor(author1);
        book.setCopiesAvailable(avCopies);
        book.setCategory(category);

        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public Book MarkAsTaken(Long id) {
        Optional<Book> book = this.findById(id);
        Integer avCopies = book.get().getCopiesAvailable() - 1;
        if(avCopies<=0){
            book.get().setCopiesAvailable(0);
        }
        else {book.get().setCopiesAvailable(avCopies);}

        return this.bookRepository.save(book.get());
    }
}