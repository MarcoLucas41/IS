DROP TABLE IF EXISTS consumer_media CASCADE;
DROP TABLE IF EXISTS media CASCADE;
DROP TABLE IF EXISTS consumer CASCADE;



--DROP TABLE IF EXISTS viewer;


--CREATE TABLE media (
--   id SERIAL PRIMARY KEY,
--    title VARCHAR(100) NOT NULL,
--    release_date DATE NOT NULL,
--    type VARCHAR(100) NOT NULL
--);
--CREATE TABLE viewer (
--    id SERIAL PRIMARY KEY,
--    name VARCHAR(100) NOT NULL,
--    age INT NOT NULL,
--    gender VARCHAR(15) NOT NULL
--);

CREATE TABLE media (
	id SERIAL PRIMARY KEY,
	title VARCHAR(100) NOT NULL,
	release_date DATE NOT NULL,
	avg_rating FLOAT NOT NULL DEFAULT 0,
	type VARCHAR(100) NOT NULL
);

CREATE TABLE consumer (
	id SERIAL PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	age	 SMALLINT NOT NULL,
	gender VARCHAR(10) NOT NULL
);

CREATE TABLE relationship (
	consumer_id SERIAL,
	media_id SERIAL,
    rating SMALLINT,
	PRIMARY KEY(consumer_id,media_id)
)

CREATE TABLE consumer_media (
    id SERIAL PRIMARY KEY,
	consumer_id BIGINT,
	media_id BIGINT,
	rating FLOAT NOT NULL
);

ALTER TABLE consumer_media ADD CONSTRAINT consumer_media_fk1 FOREIGN KEY (consumer_id) REFERENCES consumer(id);
ALTER TABLE consumer_media ADD CONSTRAINT consumer_media_fk2 FOREIGN KEY (media_id) REFERENCES media(id);

-- Insert data into the movies table
INSERT INTO media (title, release_date, type) VALUES
('Inception', '2010-07-16', 'Science Fiction'),
('The Godfather', '1972-03-24', 'Crime Drama'),
('Pulp Fiction', '1994-10-14', 'Crime Thriller'),
('The Dark Knight', '2008-07-18', 'Superhero'),
('Forrest Gump', '1994-07-06', 'Drama'),
('Robocop','1987-10-23', 'Science Fiction');


-- Insert data into the people table
--INSERT INTO viewer (name, age, gender) VALUES
INSERT INTO consumer (name, age, gender) VALUES
('Leonardo DiCaprio', 48, 'Male'),
('Marlon Brando', 80, 'Male'),
('Uma Thurman', 53, 'Female'),
('Christian Bale', 49, 'Male'),
('Tom Hanks', 67, 'Male');

INSERT INTO consumer_media (consumer_id,media_id,rating) VALUES
(1,1,9.0),
(1,2,6.0),
(1,3,7.0),
(1,4,9.0),
(1,5,10.0),
(2,1,6.0),
(2,2,2.0),
(2,3,1.0),
(2,4,6.0),
(2,5,4.0);
--(3,1,9),
--(3,2,6),
--(3,3,7),
--(3,4,9),
--(3,5,10),
--(4,1,9),
--(4,2,6),
--(4,3,7),
--(4,4,9),
--(4,5,10),
--(5,1,9),
--(5,2,6),
--(5,3,7),
--(5,4,9),
--(5,5,10);
