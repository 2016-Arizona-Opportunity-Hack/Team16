/**
 * Created by Vikranth on 10/1/2016.
 */

const UPDATE_PAGE_HEADING = 'UPDATE_PAGE_HEADING';
const UPDATE_PAGE_SECTION = 'UPDATE_PAGE_SECTION';

const initialState = {
  currentPage: {
    _id: '1',
    title: 'This is the page title',
    header: 'This is the header content',
    sections: [{
      type: 'full',
      content: {
        type: 'text',
        text: 'this is the first one I am seeing'
      },
      header: 'section 1'
    }, {
      type: 'full',
      content: {
        type: 'image',
        url: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmzMFjujgib4Md4MHoGD4VoIDwqjaG3DDrylUns_rY8dgSuybA'
      },
      header: 'section 2'
    }]
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
  console.log('here');
  return {
    type: UPDATE_PAGE_SECTION,
    section,
    index
  };
}
