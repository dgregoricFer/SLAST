import {
  Modal,
  Box,
  TextField,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
  Button,
  SelectChangeEvent,
  List,
  ListItemButton,
} from "@mui/material";
import { useEffect, useState } from "react";

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 400,
  bgcolor: "background.paper",
  border: "2px solid #000",
  boxShadow: 24,
  p: 4,
  borderRadius: 8,
};

const AddModal = ({ open, setOpen, idSlastice }) => {
  const [items, setItems] = useState([]);
  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);
  const [data, setData] = useState({
    ime: "",
    prezime: "",
    korisnickoIme: "",
    isVoditelj: false,
    mjesto: { nazivMjesta: "" },
  });

  useEffect(() => {
    fetch("http://localhost:8080/api/v1/namirnice")
      .then((res) => res.json())
      .then(
        (result) => {
          setIsLoaded(true);
          setItems(result);
        },
        (error) => {
          setIsLoaded(true);
          setError(error);
        }
      );
  }, []);

  const onSave = () => {
    const requestOptions = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    };
    fetch("http://localhost:8080/api/v1/zaposlenici", requestOptions)
      .then((response) => response.json())
      .then(() => setOpen(false))
      .then(window.location.reload());
  };

  if (isLoaded) {
    return (
      <Modal
        open={open}
        onClose={() => setOpen(false)}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <TextField
            id="outlined-basic"
            label="Ime"
            variant="outlined"
            style={{ marginBottom: "16px", width: "100%" }}
            onChange={(e) => {
              setData({
                ime: e.target.value,
                prezime: data.prezime,
                korisnickoIme: data.korisnickoIme,
                isVoditelj: data.isVoditelj,
                mjesto: { nazivMjesta: data.mjesto.nazivMjesta },
              });
            }}
          />
          <TextField
            id="outlined-basic"
            label="Prezime"
            variant="outlined"
            style={{ marginBottom: "16px", width: "100%" }}
            onChange={(e) => {
              setData({
                prezime: e.target.value,
                ime: data.ime,
                korisnickoIme: data.korisnickoIme,
                isVoditelj: data.isVoditelj,
                mjesto: { nazivMjesta: data.mjesto.nazivMjesta },
              });
            }}
          />
          <TextField
            id="outlined-basic"
            label="Korisničko ime"
            variant="outlined"
            style={{ marginBottom: "16px", width: "100%" }}
            onChange={(e) => {
              setData({
                korisnickoIme: e.target.value,
                prezime: data.prezime,
                ime: data.ime,
                isVoditelj: data.isVoditelj,
                mjesto: { nazivMjesta: data.mjesto.nazivMjesta },
              });
            }}
          />
          <TextField
            id="outlined-basic"
            label="Mjesto"
            variant="outlined"
            style={{ marginBottom: "16px", width: "100%" }}
            onChange={(e) => {
              setData({
                ime: data.prezime,
                prezime: data.prezime,
                korisnickoIme: data.korisnickoIme,
                isVoditelj: data.isVoditelj,
                mjesto: { nazivMjesta: e.target.value },
              });
            }}
          />
          <Button onClick={() => onSave()}>Spremi </Button>
        </Box>
      </Modal>
    );
  }
};

export default AddModal;
