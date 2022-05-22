import {
  Paper,
  Table,
  TableContainer,
  TableHead,
  TableRow,
  TableCell,
  TableBody,
  styled as styledd,
  tableCellClasses,
  TextField,
  Button,
  IconButton,
} from "@mui/material";
import { MdDelete, MdOutlineSave } from "react-icons/md";
import ChangeEvent, { useEffect, useState } from "react";
import styled from "styled-components";
import Details from "./Details";

const ButtonContainer = styled.div`
  display: flex;
  flex-direction: column;
`;

const StyledTableCell = styledd(TableCell)(({ theme }) => ({
  [`&.${tableCellClasses.head}`]: {
    backgroundColor: theme.palette.common.black,
    color: theme.palette.common.white,
  },
  [`&.${tableCellClasses.body}`]: {
    fontSize: 14,
  },
}));

const StyledTableRow = styledd(TableRow)(({ theme }) => ({
  "&:nth-of-type(odd)": {
    backgroundColor: theme.palette.action.hover,
  },
  // hide last border
  "&:last-child td, &:last-child th": {
    border: 0,
  },
}));

const Container = styled.div`
  padding: 30px 130px;
  display: flex;
  flex-direction: column;
`;

const Slastice = () => {
  const [items, setItems] = useState([]);
  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);

  const [detailed, setDetailed] = useState("");
  const [naziv, setNaziv] = useState("");
  const [changed, setChanged] = useState();

  const handleDelete = (uuid) => {
    fetch("http://localhost:8080/api/v1/slastice/" + uuid, {
      method: "DELETE",
    }).then(window.location.reload());
  };

  const handleSave = (uuid) => {
    const requestOptions = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ uuid: uuid, nazivSlastice: naziv }),
    };
    fetch("http://localhost:8080/api/v1/slastice/update", requestOptions)
      .then((response) => response.json())
      .then(window.location.reload());
  };

  useEffect(() => {
    fetch("http://localhost:8080/api/v1/slastice")
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

  const search = (event: ChangeEvent<HTMLInputElement>) => {
    if (event.target.value === "") {
      fetch("http://localhost:8080/api/v1/slastice")
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
    } else {
      fetch(
        "http://localhost:8080/api/v1/slastice/filter/" + event.target.value
      )
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
    }
  };

  if (isLoaded) {
    return (
      <Container>
        <div style={{ display: "flex" }}>
          <TextField
            id="txt"
            label="Naziv"
            placeholder="Pretraži po nazivu"
            onChange={search}
            color="secondary"
            focused
            fullWidth
          />
          <Button
            color="secondary"
            variant="contained"
            style={{ marginLeft: "16px" }}
          >
            Dodaj slasticu
          </Button>
        </div>
        <div style={{ display: "flex", marginTop: "16px" }}>
          <TableContainer
            sx={{ maxWidth: 450, minWidth: 450 }}
            component={Paper}
          >
            <Table
              sx={{ maxWidth: 450, minWidth: 450 }}
              aria-label="simple table"
            >
              <TableHead>
                <TableRow>
                  <StyledTableCell>Id</StyledTableCell>
                  <StyledTableCell>Naziv</StyledTableCell>
                  <StyledTableCell></StyledTableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {items.map((row, index) => (
                  <StyledTableRow
                    key={row.uuid}
                    sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
                  >
                    <StyledTableCell
                      component="th"
                      scope="row"
                      onClick={() => setDetailed(row)}
                      style={{ cursor: "pointer" }}
                    >
                      {index + 1}
                    </StyledTableCell>
                    <StyledTableCell align="right">
                      <TextField
                        label=""
                        variant="standard"
                        value={
                          changed === index ? naziv || row.naziv : row.naziv
                        }
                        onChange={(e) => {
                          setNaziv(e.target.value);
                          setChanged(index);
                        }}
                        fullWidth
                      />
                    </StyledTableCell>
                    <StyledTableCell align="right">
                      <ButtonContainer>
                        <IconButton
                          color="error"
                          aria-label="delete"
                          onClick={() => handleDelete(row.uuid)}
                        >
                          <MdDelete />
                        </IconButton>
                        <IconButton
                          disabled={naziv === "" || changed !== index}
                          color="success"
                          aria-label="edit"
                          onClick={() => {
                            handleSave(row.uuid);
                          }}
                        >
                          <MdOutlineSave />
                        </IconButton>
                      </ButtonContainer>
                    </StyledTableCell>
                  </StyledTableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
          <Paper style={{ flexGrow: 1, marginLeft: "24px", padding: "16px" }}>
            {detailed && <Details item={detailed} />}
          </Paper>
        </div>
      </Container>
    );
  }
};

export default Slastice;
