Library amanagement system
A Library Management System is a software built to handle the primary housekeeping functions of a library. Libraries
rely on library management systems to manage asset collections as well as relationships with their members. Library
management systems help libraries keep track of the books and their checkouts, as well as members’ subscriptions and
profiles.
Library management systems also involve maintaining the database for entering new books and recording books that have
been borrowed with their respective due dates.

Questions:

1. Types of assets we support? books, magazines
2. What are subscriptions?

FRs:

1. Members search catalog for books, finds location and gets them
2. Member can borrow one or more books if available
   1.1 Members can return a book for certain duration
2. Members can place unavailable book on hold, and get notified when it becomes available
3. Admin can add new books + quantity OR update quantities for existing books
4. Admin can create and manage member profiles
5. System sends notification to member: book return due in x days, book return overdue
6. System charges members if book overdue, based on per day upto a maximum charge
    7. Members flagged as overdue can pay their fees using cash or card

NFRs:

1. Avoid race conditions when borrowing books

MISSED:

1. Searching books -> provides location of

We will focus on the following set of requirements while designing the Library Management System:

1. Any library member should be able to search books by their title, author, subject category as well by the publication
   date.
2. Each book will have a unique identification number and other details including a rack number which will help to
   physically locate the book.
3. There could be more than one copy of a book, and library members should be able to check-out and reserve any copy. We
   will call each copy of a book, a book item.
4. The system should be able to retrieve information like who took a particular book or what are the books checked-out
   by a specific library member.
5. There should be a maximum limit (5) on how many books a member can check-out.
6. There should be a maximum limit (10) on how many days a member can keep a book.
7. The system should be able to collect fines for books returned after the due date.
8. Members should be able to reserve books that are not currently available.
9. The system should be able to send notifications whenever the reserved books become available, as well as when the
   book is not returned within the due date.
10. Each book and member card will have a unique barcode. The system will be able to read barcodes from books and
    members’ library cards.
    Use case diagram

Core entities:

1. Account of type Member(id, info ), Librarian(id, info) and System
2. BookDetails
   - title
   - author
   - Subject
   - publishedDate
3. BookItem
   - id
   - details : BookDetails
4. Catalog: AuthorCatalog, TitleCatalog, SubjectCatalog, 
5. Library
6. Rack
7. Book Reservation
8. 


Relathionships:

1. LMS has 1 Library, N accounts, 1 NotificationService, 1 Payment, 1 Fees
2. Library has many books
3. 