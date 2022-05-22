import {
  Table,
  TableContainer,
  TableHead,
  TableRow,
  TableCell,
  TableBody,
  IconButton,
} from "@mui/material";
import { FormattedDate, FormattedTime } from "react-intl";
import styled from "styled-components";
import { MdDelete, MdBorderColor } from "react-icons/md";
import { useState } from "react";
import EditModal from "./EditModal";
import AddSastojak from "./AddSastojak";

const ButtonContainer = styled.div`
  display: flex;
  flex-direction: column;
`;

const Details = ({ item }) => {
  const [open, setOpen] = useState(false);
  const [selected, setSelected] = useState();

  const handleDelete = (uuid) => {
    fetch("http://localhost:8080/api/v1/namirnice/" + uuid, {
      method: "DELETE",
    }).then(() => window.location.reload());
  };

  const handleClick = (row) => {
    setSelected(row);
    setOpen(true);
  };

  return (
    <>
      <div style={{ display: "flex", justifyContent: "space-between" }}>
        <h2 style={{ marginTop: 0 }}>{item.naziv}</h2>
        <AddSastojak idSlastice={item.uuid} />
      </div>

      <TableContainer>
        <Table sx={{ minWidth: 100 }} aria-label="simple table">
          <TableHead>
            <TableRow>
              <TableCell>Id</TableCell>
              <TableCell align="right">Naziv</TableCell>
              <TableCell align="right">Količina</TableCell>
              <TableCell align="right">Zadnja promjena</TableCell>
              <TableCell></TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {item.namirnice.map((row) => (
              <TableRow
                key={row.idNamirnice}
                sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
              >
                <TableCell sx={{ maxWidth: 50 }} component="th" scope="row">
                  {row.idNamirnice}
                </TableCell>
                <TableCell align="right">{row.nazivNamirnice}</TableCell>
                <TableCell align="right">{row.kolicina}</TableCell>
                <TableCell align="right">
                  <FormattedDate
                    value={Date.parse(row.zadnjaPromjena)}
                    year="numeric"
                    month="long"
                    day="2-digit"
                  />
                  <FormattedTime value={Date.parse(row.zadnjaPromjena)} />
                </TableCell>
                <TableCell>
                  <ButtonContainer>
                    <IconButton
                      color="error"
                      aria-label="delete"
                      onClick={() => handleDelete(row.uuid)}
                    >
                      <MdDelete />
                    </IconButton>
                    <IconButton
                      color="primary"
                      aria-label="edit"
                      onClick={() => {
                        handleClick(row);
                      }}
                    >
                      <MdBorderColor />
                    </IconButton>
                  </ButtonContainer>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
      {open && selected && (
        <EditModal open={open} setOpen={setOpen} namirnica={selected} />
      )}
    </>
  );
};

export default Details;
