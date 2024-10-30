DROP TABLE IF EXISTS relationship CASCADE;
DROP TABLE IF EXISTS consumers_media CASCADE;
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
	avg_rating SMALLINT NOT NULL DEFAULT 1,
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
	PRIMARY KEY(consumer_id,media_id)
);

ALTER TABLE relationship ADD CONSTRAINT relationship_fk1 FOREIGN KEY (consumer_id) REFERENCES consumer(id);
ALTER TABLE relationship ADD CONSTRAINT relationship_fk2 FOREIGN KEY (media_id) REFERENCES media(id);

-- Insert data into the movies table
INSERT INTO media (title, release_date, type) VALUES
('Inception', '2010-07-16', 'Science Fiction'),
('The Godfather', '1972-03-24', 'Crime Drama'),
('Pulp Fiction', '1994-10-14', 'Crime Thriller'),
('The Dark Knight', '2008-07-18', 'Superhero'),
('Forrest Gump', '1994-07-06', 'Drama');

-- Insert data into the people table
--INSERT INTO viewer (name, age, gender) VALUES
INSERT INTO consumer (name, age, gender) VALUES
('Leonardo DiCaprio', 48, 'Male'),
('Marlon Brando', 80, 'Male'),
('Uma Thurman', 53, 'Female'),
('Christian Bale', 49, 'Male'),
('Tom Hanks', 67, 'Male');


