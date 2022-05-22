import {
  FormControl,
  InputLabel,
  MenuItem,
  Select,
  SelectChangeEvent,
} from "@mui/material";
import { useEffect, useState } from "react";

const AddSastojak = ({ idSlastice }) => {
  const [items, setItems] = useState([]);
  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);

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

  const onSave = (event: SelectChangeEvent) => {
    const requestOptions = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
    };
    fetch(
      "http://localhost:8080/api/v1/slastice/" +
        event.target.value +
        "/" +
        idSlastice,
      requestOptions
    )
      .then((response) => response.json())
      .then(window.location.reload());
  };
  if (isLoaded) {
    return (
      <FormControl sx={{ width: 300 }}>
        <InputLabel id="demo-simple-select">Dodaj sastojak</InputLabel>
        <Select
          labelId="demo-simple-select"
          id="simple-select"
          label="Sastojak"
          onChange={onSave}
        >
          {items.map((item) => {
            return <MenuItem value={item.uuid}>{item.nazivNamirnice}</MenuItem>;
          })}
        </Select>
      </FormControl>
    );
  }
};

export default AddSastojak;
