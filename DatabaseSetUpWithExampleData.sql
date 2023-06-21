CREATE TABLE BOOKS (
    ID BIGINT,
    AUTHOR VARCHAR(255),
    PUBLICATION_YEAR INT,
    TITLE VARCHAR(255),
    PRIMARY KEY (ID)
);

CREATE TABLE BOOK_COPIES (
    ID BIGINT,
    STATUS VARCHAR(255),
    BOOK_ID  BIGINT,
    PRIMARY KEY (ID),
    FOREIGN KEY (BOOK_ID) REFERENCES BOOKS(ID)
);

CREATE TABLE MOVIES (
    ID BIGINT,
    DIRECTOR VARCHAR(255),
    RELEASE_YEAR INT,
    TITLE VARCHAR(255),
    PRIMARY KEY (ID)
);

CREATE TABLE MOVIE_COPIES (
    ID BIGINT,
    STATUS VARCHAR(255),
    MOVIE_ID BIGINT,
    PRIMARY KEY (ID),
    FOREIGN KEY (MOVIE_ID) REFERENCES MOVIES(ID)
);

INSERT INTO BOOKS (ID, AUTHOR, PUBLICATION_YEAR, TITLE)
VALUES (1, "Bob Dylan", 2022, "The Philosophy of Modern Song"),
       (2, "Dr. Sonu Phogat", 2022, "Ashtang Yoga"),
       (3, "Harish Mehta", 2022, "The Maverick Effect"),
       (4, "Ashok Pangariya", 2021, "Monk in a Merc"),
       (5, "Chetan Bhagat", 2021, "400 Days");

INSERT INTO BOOK_COPIES (ID, STATUS, BOOK_ID)
VALUES (1, "Available", 1),
       (2, "Rented", 1),
       (3, "Available", 3),
       (4, "Available", 2),
       (5, "Rented", 2),
       (6, "Available", 4),
       (7, "Available", 4),
       (8, "Rented", 5),
       (9, "Available", 1),
       (10, "Rented", 5);

INSERT INTO MOVIES (ID, DIRECTOR, RELEASE_YEAR, TITLE)
VALUES (1, "Andrzej Zulawski", 1981, "Possesion"),
       (2, "David CronenBerg", 1983, "Videodrome"),
       (3, "Robert Bresson", 1966, "Mouchette"),
       (4, "Michael Cimino", 1978, "The Deer Hunter"),
       (5, "F.W. Murnau", 1922, "Nosferatu");

INSERT INTO MOVIE_COPIES (ID, STATUS, MOVIE_ID)
VALUES (1, "Available", 1),
       (2, "Rented", 1),
       (3, "Available", 3),
       (4, "Available", 2),
       (5, "Rented", 2),
       (6, "Available", 4),
       (7, "Available", 4),
       (8, "Rented", 5),
       (9, "Available", 1),
       (10, "Rented", 5);