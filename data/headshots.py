import os
import psycopg2
import logging
from dotenv import load_dotenv
import pandas as pd

# Load environment variables from .env file
load_dotenv()

# Configure logging
logging.basicConfig(level=logging.INFO)

# Read connection parameters from environment variables
conn_params = {
    'dbname': os.getenv('DB_NAME'),
    'user': os.getenv('DB_USER'),
    'password': os.getenv('DB_PASSWORD'),
    'host': os.getenv('DB_HOST'),
    'port': int(os.getenv('DB_PORT'))
}

# Establish a connection to the database
conn = psycopg2.connect(**conn_params)

# Add the photo column if it doesn't exist
def add_photo_column(conn):
    alter_table_query_common_player_info = """
    ALTER TABLE common_player_info
    ADD COLUMN IF NOT EXISTS photo VARCHAR(255);
    """
    alter_table_query_current_player_stats = """
    ALTER TABLE current_player_stats
    ADD COLUMN IF NOT EXISTS photo VARCHAR(255);
    """

    alter_table_query_league_leaders = """
    ALTER TABLE league_leaders
    ADD COLUMN IF NOT EXISTS photo VARCHAR(255);
    """

    with conn.cursor() as cur:
        cur.execute(alter_table_query_common_player_info)
        cur.execute(alter_table_query_current_player_stats)
        cur.execute(alter_table_query_league_leaders)
        conn.commit()
    logging.info("Photo column added to common_player_info, current_player_stats and league_leaders tables.")

# Function to update player photos in the database
def update_player_photos(conn, csv_file_path):
    df = pd.read_csv(csv_file_path)

    base_image_dir = os.getenv('BASE_IMAGE_DIR')

    for index, row in df.iterrows():
        player_id = row['playerid'] 
        image_filename = f"{player_id}.png"

        # Construct the absolute image path
        image_path = os.path.join(base_image_dir, image_filename)

        # Check if the image file exists
        if os.path.exists(image_path):
            # Create the relative path to store in the database
            relative_image_path = f"/archive/img/{image_filename}"

            # Update common_player_info table
            update_query_common_player_info = """
            UPDATE common_player_info
            SET photo = %s
            WHERE player_id = %s;
            """
            with conn.cursor() as cur:
                cur.execute(update_query_common_player_info, (relative_image_path, player_id))

            # Update current_player_stats table
            update_query_current_player_stats = """
            UPDATE current_player_stats
            SET photo = %s
            WHERE player_id = %s;
            """
            with conn.cursor() as cur:
                cur.execute(update_query_current_player_stats, (relative_image_path, player_id))

            # Update league_leaders table
            update_query_league_leaders = """
            UPDATE league_leaders
            SET photo = %s
            WHERE player_id = %s;
            """
            with conn.cursor() as cur:
                cur.execute(update_query_league_leaders, (relative_image_path, player_id))
            
            conn.commit()
            logging.info(f"Updated photo for Player ID: {player_id} (Stored Image: {relative_image_path}).")
        else:
            logging.warning(f"Image not found for Player ID: {player_id} (Expected Image: {image_path}).")

# Ensure the photo column exists
add_photo_column(conn)

# Example usage
csv_file_path = 'data/players.csv'
update_player_photos(conn, csv_file_path)

# Close the connection
conn.close()
