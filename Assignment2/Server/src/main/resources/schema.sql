DROP TABLE IF EXISTS consumer_media CASCADE;
DROP TABLE IF EXISTS media CASCADE;
DROP TABLE IF EXISTS consumer CASCADE;

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
    age SMALLINT NOT NULL,
    gender VARCHAR(10) NOT NULL
);

CREATE TABLE consumer_media (
    id SERIAL PRIMARY KEY,
    consumer_id INTEGER,
    media_id INTEGER,
    rating FLOAT NOT NULL
);

ALTER TABLE consumer_media ADD CONSTRAINT consumer_media_fk1 FOREIGN KEY (consumer_id) REFERENCES consumer(id);
ALTER TABLE consumer_media ADD CONSTRAINT consumer_media_fk2 FOREIGN KEY (media_id) REFERENCES media(id);

CREATE OR REPLACE FUNCTION update_avg_rating() RETURNS TRIGGER AS '
BEGIN
    UPDATE media
    SET avg_rating = (
        SELECT AVG(rating)
        FROM consumer_media
        WHERE media_id = NEW.media_id
    )
    WHERE id = NEW.media_id;
    RETURN NEW;
END;
' LANGUAGE plpgsql;

-- Create the trigger that calls the function
CREATE TRIGGER trg_update_avg_rating
AFTER INSERT OR UPDATE OR DELETE ON consumer_media
FOR EACH ROW
EXECUTE FUNCTION update_avg_rating();

-- Insert data into the media table
INSERT INTO media (title, release_date, type) VALUES
('Inception', '2010-07-16', 'Science Fiction'),
('The Godfather', '1972-03-24', 'Crime Drama'),
('Pulp Fiction', '1984-10-14', 'Crime Thriller'),
('The Dark Knight', '2008-07-18', 'Superhero'),
('Forrest Gump', '1984-07-06', 'Drama'),
('Robocop', '1987-10-23', 'Science Fiction');

-- Insert data into the consumer table
INSERT INTO consumer (name, age, gender) VALUES
('James', 48, 'Male'),
('Issac', 80, 'Male'),
('Rachel', 53, 'Female'),
('Leon', 49, 'Male'),
('Max', 67, 'Male');

-- Insert ratings into the consumer_media table
INSERT INTO consumer_media (consumer_id, media_id, rating) VALUES
(1, 1, 9.0),
(1, 2, 6.0),
(1, 3, 7.0),
(1, 4, 9.0),
(1, 5, 10.0),
--(1, 6, 5),
(2, 1, 6.0),
(2, 2, 2.0),
(2, 3, 1.0),
(2, 4, 6.0),
(2, 5, 10.0);
--(2, 6, 7.0);
