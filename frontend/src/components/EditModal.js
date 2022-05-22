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
} from "@mui/material";
import { useState } from "react";

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

const EditModal = ({
  open,
  setOpen,
  namirnica = {
    uuid: "",
    nazivNamirnice: "",
    kolicina: "",
    zadnjaPromjena: "",
  },
}) => {
  //fetch kolicina
  const [data, setData] = useState(namirnica);

  const handleChange = (event: SelectChangeEvent) => {
    setData({
      uuid: data.uuid,
      nazivNamirnice: data.nazivNamirnice,
      kolicina: event.target.value,
      zadnjaPromjena: Date.now(),
    });
  };

  const onSave = () => {
    const requestOptions = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    };
    fetch("http://localhost:8080/api/v1/namirnice/update", requestOptions)
      .then((response) => response.json())
      .then(() => setOpen(false))
      .then(window.location.reload());
  };

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
          label="Naziv"
          variant="outlined"
          defaultValue={data.nazivNamirnice}
          style={{ marginBottom: "16px", width: "100%" }}
          InputLabelProps={{ shrink: true }}
          onBlur={(event) =>
            setData({
              uuid: data.uuid,
              nazivNamirnice: event.target.value,
              kolicina: data.kolicina,
              zadnjaPromjena: Date.now(),
            })
          }
        />
        <FormControl fullWidth>
          <InputLabel id="demo-simple-select-label">Količina</InputLabel>
          <Select
            labelId="demo-simple-select-label"
            id="demo-simple-select"
            value={data.kolicina}
            label="Količina"
            onChange={handleChange}
          >
            <MenuItem value={10}>Ten</MenuItem>
            <MenuItem value={20}>Twenty</MenuItem>
            <MenuItem value={30}>Thirty</MenuItem>
          </Select>
        </FormControl>
        <div style={{ marginTop: "16px", float: "right" }}>
          <Button color="primary" variant="contained" onClick={onSave}>
            Spremi
          </Button>
        </div>
      </Box>
    </Modal>
  );
};

export default EditModal;
