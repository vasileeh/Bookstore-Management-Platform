package com.example.bookstore.viewController;

import com.example.bookstore.service.BookService;
import com.example.bookstore.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class BookViewController {

    private final BookService bookService;

    @GetMapping("/books")
    public String viewCatalog(Model model,
                              @RequestParam(value = "filter", required = false) String filter,
                              @RequestParam(value = "filterValue", required = false) String filterValue) {
        var books = (filter == null || filterValue == null)
                ? bookService.getAllBooks()
                : bookService.filterBooks(filter, filterValue);
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/books/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "addBook";
    }

    @PostMapping("/books/add")
    public String addBook(@ModelAttribute Book book) {
        bookService.addBook(book);
        return "redirect:/books";
    }

    @PostMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}
