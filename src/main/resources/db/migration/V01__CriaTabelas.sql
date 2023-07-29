-- Create the TBminute table
CREATE TABLE TBminute (
    id SERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    end_at TIMESTAMP NOT NULL,
    description VARCHAR(255) NOT NULL
);

-- Create the TBvotes table
CREATE TABLE TBvotes (
    id SERIAL PRIMARY KEY,
    id_minute BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (id_minute) REFERENCES TBminute (id),
    CONSTRAINT unique_vote UNIQUE (id_minute, user_id)
);
