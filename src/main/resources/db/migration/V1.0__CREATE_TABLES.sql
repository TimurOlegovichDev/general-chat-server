CREATE TABLE IF NOT EXISTS client (
     user_name VARCHAR(255) PRIMARY KEY,
     password VARCHAR(255) NOT NULL,
     address INET NOT NULL,
     port INT NOT NULL
);

CREATE TABLE IF NOT EXISTS message (
     uuid UUID PRIMARY KEY,
     text TEXT NOT NULL,
     user_name VARCHAR(255) NOT NULL,
     FOREIGN KEY (user_name)
     REFERENCES client(user_name) ON DELETE CASCADE
);
