/**
 * Created by Vikranth on 10/1/2016.
 */

const UPDATE_PAGE_HEADING = 'UPDATE_PAGE_HEADING';
const UPDATE_PAGE_SECTION = 'UPDATE_PAGE_SECTION';

const ADD_PAGE_SECTION = 'ADD_PAGE_SECTION';
const UPDATE_PAGE_ID = 'UPDATE_PAGE_ID';
const DELETE_SECTION = 'DELETE_SECTION';

const initialState = {
  currentPage: {
    title: 'Sample title',
    header: 'Sample heading',
    sections: []
  },
  navigation: [],
  pages: [],
  isEditing: false,
  isFetching: false
};

export default function reducer(state = initialState, action = {}) {
  const currentPage = state.currentPage;
  switch (action.type) {
    case UPDATE_PAGE_HEADING:
      return {
        ...state,
        currentPage: {
          ...currentPage,
          header: action.header
        }
      };
    case UPDATE_PAGE_SECTION:
      const sections = [
        ...currentPage.sections
      ];
      sections[action.index] = {
        ...action.section
      };
      return {
        ...state,
        currentPage: {
          ...currentPage,
          sections
        }
      };
    case UPDATE_PAGE_ID:
      return {
        ...state,
        currentPage: {
          ...currentPage,
          _id: action._id
        }
      };
    case ADD_PAGE_SECTION:
      return {
        ...state,
        currentPage: {
          ...currentPage,
          sections: [...currentPage.sections, action.section]
        }
      };
    case DELETE_SECTION:
      const deleteUpdateSections = [...currentPage.sections];
      deleteUpdateSections.splice(action.index, 1);
      return {
        ...state,
        currentPage: {
          ...currentPage,
          sections: deleteUpdateSections
        }
      };
    default:
      return state;
  }
}

export function updatePageHeader(header) {
  return {
    type: UPDATE_PAGE_HEADING,
    header: header
  };
}

export function updatePageSection(index, section) {
  return {
    type: UPDATE_PAGE_SECTION,
    section,
    index
  };
}

export function addPageSection(section) {
  return {
    type: ADD_PAGE_SECTION,
    section
  };
}

export function updatePageId(_id) {
  return {
    type: UPDATE_PAGE_ID,
    _id
  };
}

export function deleteSection(index) {
  return {
    type: DELETE_SECTION,
    index
  };
}
