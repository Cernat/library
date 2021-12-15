
export class Book {
    id ?: string;
    author ?: string;
    title ?: string;
    numberOfPages ?: number;

    constructor(object: any) {
        this.id = object.id;
        this.author = object.author;
        this.title = object.title;
        this.numberOfPages = object.numberOfPages;
    }
}